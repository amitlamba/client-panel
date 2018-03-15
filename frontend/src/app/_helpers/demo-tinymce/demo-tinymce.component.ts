import {Component, ElementRef, NgZone, Input, ViewChild, EventEmitter, Output} from '@angular/core';
import {MentionDirective} from "../mention/mention.directive";
import {UserParams} from "../../_models/user";


declare var tinymce: any;

/**
 * Angular 2 Mentions.
 * https://github.com/dmacfarlane/angular-mentions
 *
 * Example usage with TinyMCE.
 */
@Component({
  selector: 'app-demo-tinymce',
  template: `
    <div class="form-group" style="position:relative">
      <div [mention]="items"></div>
      <div>
        <textarea class="hidden" cols="60" rows="4" id="tmce" [(ngModel)]="htmlContent">{{htmlContent}}</textarea>
      </div>
    </div>`
})
export class DemoTinymceComponent {
  localHtmlContent: string = "Text";
  @Input() get htmlContent(): string {return this.localHtmlContent};
  set htmlContent(htmlContent: string) {
    this.localHtmlContent = htmlContent;
    this.htmlContentChange.emit(this.localHtmlContent);
  }
  @Output() htmlContentChange = new EventEmitter();

  @ViewChild(MentionDirective) mention: MentionDirective;
  public items:string[] = UserParams.params;
  constructor(private _elementRef: ElementRef, private _zone: NgZone) {}
  ngAfterViewInit() {
    tinymce.init({
      mode: 'exact',
      height: 100,
      theme: 'modern',
      branding: false,
      plugins: [
        'advlist autolink lists link image charmap print preview anchor',
        'searchreplace visualblocks code fullscreen',
        'insertdatetime media table contextmenu paste code'
      ],
      toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
      elements: "tmce",
      setup: this.tinySetup.bind(this)
    }
    );
  }
  tinySetup(ed) {
    let comp = this;
    let mention = this.mention;
    ed.on('keydown', function(e) {
      let frame = <any>window.frames[ed.iframeElement.id];
      let contentEditable = frame.contentDocument.getElementById('tinymce');
      comp._zone.run(() => {
        comp.mention.keyHandler(e, contentEditable);
      });
    });
    ed.on('init', function(args) {
      mention.setIframe(ed.iframeElement);
      // ed.setContent(this.htmlContent);
    });
    ed.on('keyup change', (e) => {
      let frame = <any>window.frames[ed.iframeElement.id];
      console.log(frame.contentDocument.getElementById('tinymce'));
      this.htmlContent = e.target.innerHTML;
      console.log(e.target.innerHTML);
      console.log(this.htmlContent);
    });
  }
}
