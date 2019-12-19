import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {TemplateService} from '../service/template.service';
import {User} from '../model/user';
import {AuthenticationService} from '../service/authentication.service';
import {Router} from '@angular/router';
import {MatTableDataSource} from '@angular/material/table';
import {Subscription} from 'rxjs';
import {MatDialog, MatSort} from '@angular/material';
import {ModalComponent} from '../modal/modal.component';
import {Template} from '../model/template';

@Component({templateUrl: './home.component.html',
            styleUrls: ['./home.component.scss']})
export class HomeComponent implements OnDestroy, OnInit {
  currentUser: User;
  subscription: Subscription;
  deleteSubscription: Subscription;
  displayedColumns: string[] = ['name', 'mailto', 'subject', 'cc', 'edit', 'delete'];
  dataSource = new MatTableDataSource();

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private authenticationService: AuthenticationService,
              private templateService: TemplateService,
              public matDialog: MatDialog,
              private router: Router) {
    this.currentUser = this.authenticationService.currentUserValue;
    // redirect to home if already logged in
    if (!this.currentUser) {
      this.router.navigate(['/login']);
    }
    this.loadData();
  }

  ngOnInit(): void {
    this.dataSource.sort = this.sort;
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.deleteSubscription.unsubscribe();
  }

  loadData() {
    this.subscription = this.templateService.getTemplates(this.currentUser.identifier).subscribe(body => {
      this.dataSource.data = body;
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  new() {
    this.openDialog('Neues Template erstellen:',  new Template());
  }

  edit(element) {
    this.openDialog('Template editieren:', JSON.parse(JSON.stringify(element)));
  }

  delete(element) {
    this.deleteSubscription = this.templateService.deleteTemplate(element.identifier).subscribe();
  }

  openDialog(title: string, template: Template) {
    const dialogRef = this.matDialog.open(ModalComponent, {
      width: '60%',
      autoFocus: true,
      disableClose: true,
      data: {title, template, currentUser: this.currentUser},
      panelClass: 'custom-dialog-container'
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) { this.loadData(); }
    });
  }
}

