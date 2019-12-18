import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {TemplateService} from '../service/template.service';
import {User} from '../model/user';
import {AuthenticationService} from '../service/authentication.service';
import {Router} from '@angular/router';
import {MatTableDataSource} from '@angular/material/table';
import {Subscription} from 'rxjs';
import {MatSort} from '@angular/material';

@Component({templateUrl: './home.component.html',
            styleUrls: ['./home.component.scss']})
export class HomeComponent implements OnDestroy, OnInit {
  currentUser: User;
  subscription: Subscription;
  displayedColumns: string[] = ['name', 'mailto', 'subject', 'cc', 'edit', 'delete'];
  dataSource = new MatTableDataSource();

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private templateService: TemplateService,
              private authenticationService: AuthenticationService,
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
  }

  loadData() {
    this.subscription = this.templateService.getTemplates(this.currentUser.identifier).subscribe(body => {
      this.dataSource.data = body;
    });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  edit() {

  }

  delete() {

  }
}

