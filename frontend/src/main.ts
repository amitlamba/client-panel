import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { Http } from '@angular/http';
import { HttpModule} from '@angular/http';

import { AppModule } from './app/app.module';


platformBrowserDynamic().bootstrapModule(AppModule);
