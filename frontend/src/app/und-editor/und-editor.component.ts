import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-und-editor',
  templateUrl: './und-editor.component.html',
  styleUrls: ['./und-editor.component.css']
})
export class UndEditorComponent implements OnInit {

  public editorValue: string = '';

  constructor() { }

  ngOnInit() {
  }

}
