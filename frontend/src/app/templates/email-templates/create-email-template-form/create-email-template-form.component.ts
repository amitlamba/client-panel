import {Component, ElementRef, Input, OnChanges, OnInit, ViewChild} from '@angular/core';
import {EmailTemplate} from "../../../_models/email";
import {TemplatesService} from "../../../_services/templates.service";
import {isNullOrUndefined} from "util";
import {Router} from "@angular/router";
import {MessageService} from "../../../_services/message.service";
import {UserFields} from "../../../_settings/app-settings";
import {UserParams} from "../../../_models/user";

@Component({
  selector: 'app-create-email-template-form',
  templateUrl: './create-email-template-form.component.html',
  styleUrls: ['./create-email-template-form.component.scss']
})
export class CreateEmailTemplateFormComponent implements OnInit, OnChanges {

  showRichTextEditor: boolean = true;
  emailTemplate: EmailTemplate = new EmailTemplate();
  @ViewChild("f") form: any;

  userFields = UserFields.USER_DETAIILS;
  items = UserParams.params;
  suggested:string[]=["laksh","kamal","a","b","c"];

  constructor(private templatesService: TemplatesService, private messageService: MessageService) {
  }

  ngOnChanges() {
  }

  ngOnInit() {
    this.templatesService.castEmailTemplateForEdit.subscribe((emailTemplateForEdit) => {
      this.emailTemplate=emailTemplateForEdit;
    });
  }

  onSave(form: FormData) {
    if (this.form.valid) {
      if (this.emailTemplate.id) {
        this.templatesService.saveEmailTemplate(this.emailTemplate)
          .subscribe(
            response => {
              this.templatesService.editEmailTemplate(this.emailTemplate);
              this.messageService.addSuccessMessage("Email Template Edited Successfully");
            }
          );
      } else {
        this.templatesService.saveEmailTemplate(this.emailTemplate)
          .subscribe(
            response => {
              console.log(response);
              this.emailTemplate.id=response
              this.templatesService.addEmailTemplate(this.emailTemplate);
              this.messageService.addSuccessMessage("Email Template Created Successfully");
            }
          );
      }
    }
  }

  keyupHandlerFunction(event: any) {

  }

}
