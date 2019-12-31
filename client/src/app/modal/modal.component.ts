import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Component, Inject, OnInit} from '@angular/core';
import {Template} from '../model/template';
import {User} from '../model/user';
import {TemplateService} from '../service/template.service';
import {first} from 'rxjs/operators';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss']
})

export class ModalComponent implements OnInit {
  submitted: boolean;
  loading: boolean;
  modalForm: FormGroup;

  constructor(public dialogRef: MatDialogRef<boolean>,
              private templateService: TemplateService,
              private formBuilder: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: {title: string, template: Template, currentUser: User}) {}

  ngOnInit(): void {
    if (!this.data.template.body) { this.data.template.body = ''; }
    this.modalForm = this.formBuilder.group({
      identifier: [this.data.template.identifier],
      name: [this.data.template.name, Validators.required],
      mailto: [this.data.template.mailto, [Validators.required, Validators.email]],
      cc: [this.data.template.cc,  Validators.email],
      bcc: [this.data.template.bcc,  Validators.email],
      subject: [this.data.template.subject],
      body: [this.data.template.body],
    });
  }

  get f() { return this.modalForm.controls; }

  onCancel() {
    this.dialogRef.close(false);
  }

  onCreate() {
    this.submitted = true;

    if (this.modalForm.invalid) {
      return;
    }

    this.loading = true; // Loading symbol in button when fetching data
    console.log(this.modalForm.value.body);
    this.templateService.createOrUpdateTemplate(this.data.currentUser.identifier, this.modalForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.dialogRef.close(true);
        },
        error => {
          this.loading = false;
        });
  }

  countOf(text) {
    const s = text ? text.split(/\w+/) : 0; // it splits the text on space/tab/enter
    return s ? s.length - 1 : 0;
  }
}
