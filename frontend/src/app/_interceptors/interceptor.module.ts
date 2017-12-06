import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Injectable, NgModule} from "@angular/core";

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (localStorage.getItem("currentUser")) {
      var currentUser = JSON.parse(localStorage.getItem('currentUser'));
      var token = currentUser && currentUser.token;
      const dupReq = req.clone({headers: req.headers.set('authorization', token)});
      console.log("Interceptor Dup Request");
      console.log(dupReq);
      return next.handle(dupReq);
    } else {
      console.log("Interceptor Request");
      console.log(req);
      return next.handle(req);
    }
  }

}

@NgModule(
  {
    providers: [
      {provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true}
    ]
  }
)
export class InterceptorModule {
}
