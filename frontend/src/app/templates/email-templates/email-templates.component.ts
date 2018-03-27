import {Component, OnInit} from '@angular/core';
import {EmailTemplate} from "../../_models/email";
import {TemplatesService} from "../../_services/templates.service";

@Component({
  selector: 'app-email-templates',
  templateUrl: './email-templates.component.html',
  styleUrls: ['./email-templates.component.css']
})
export class EmailTemplatesComponent implements OnInit {

  emailTemplates = new Array<EmailTemplate>();

  constructor(public templatesService: TemplatesService) {
  }

  ngOnInit() {
    this.templatesService.castEmailTemplates.subscribe(
      (emailTemplates) => {
        this.emailTemplates = emailTemplates;
      }
    );
    this.getEmailTemplates();
  }

  getEmailTemplates() {
    this.templatesService.getEmailTemplates().subscribe(
      (response) => {
        // this.emailTemplates = response;
        this.templatesService.addEmailTemplates(response);
      }
    );
  }

  onEdit(emailTemplate: EmailTemplate) {
    console.log(JSON.parse(JSON.stringify(emailTemplate)));
    this.templatesService.emailTemplateForEdit.next(JSON.parse(JSON.stringify(emailTemplate)));
  }

  onCancel() {
    this.templatesService.emailTemplateForEdit.next(new EmailTemplate());
  }

  onCreateNew() {
    this.templatesService.emailTemplateForEdit.next(new EmailTemplate());
  }
}
