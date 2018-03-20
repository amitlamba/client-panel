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
        <textarea class="hidden" cols="60" rows="4" id="tmce" [(ngModel)]="htmlContent">{{htmlContent}}
        </textarea>
        <button class="btn btn-primary" (click)="addUnsubscribeLink($event)" type="button">Add Unsubscribe</button>
      </div>
    </div>`
})
export class DemoTinymceComponent {
  @ViewChild('tmce') tmce: ElementRef;
  localHtmlContent: string = "Text";

  @Input() get htmlContent(): string {
    return this.localHtmlContent
  };

  set htmlContent(htmlContent: string) {
    this.localHtmlContent = htmlContent;
    this.htmlContentChange.emit(this.localHtmlContent);
  }

  @Input() content: String;
  @Output() htmlContentChange = new EventEmitter();
  @Output() onEditorContentChange: EventEmitter<any> = new EventEmitter<any>();
  @ViewChild(MentionDirective) mention: MentionDirective;
  public items: string[] = UserParams.params;

  constructor(private _elementRef: ElementRef, private _zone: NgZone) {
  }

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
    });
  }

  tinySetup(ed) {
    let comp = this;
    let mention = this.mention;
    ed.on('keydown', function (e) {
      let frame = <any>window.frames[ed.iframeElement.id];
      let contentEditable = frame.contentDocument.getElementById('tinymce');
      comp._zone.run(() => {
        comp.mention.keyHandler(e, contentEditable);
      });
    });
    ed.on('init', (e) => {
      mention.setIframe(ed.iframeElement);
      // ed.setContent(this.htmlContent);
      // console.log(args.target.getInnerHTML);
      this.htmlContent = e.target.innerHTML;
      this.onEditorContentChange.emit(e.target.innerHTML);
    });
    ed.on('keyup', (e) => {
      let frame = <any>window.frames[ed.iframeElement.id];
      this.htmlContent = e.target.innerHTML;
      this.onEditorContentChange.emit(this.localHtmlContent);
      console.log(this.localHtmlContent);
    });

  }
  addUnsubscribeLink(event) {
    if(event.srcElement.innerHTML==='Add Unsubscribe'){
      // http://archive.tinymce.com/wiki.php/API3:method.tinymce.dom.DOMUtils.add  "Below Line Definition."
      tinymce.activeEditor.dom.add(tinymce.activeEditor.getBody(), 'a', {href : '#' ,id : 'unsubscribe'} , 'Unsubscribe');
      event.srcElement.innerHTML='Remove Unsubscribe';
    }
   else {
      tinymce.activeEditor.dom.remove(tinymce.activeEditor.dom.select('#unsubscribe'));
      event.srcElement.innerHTML='Add Unsubscribe';
    }
  }
}
