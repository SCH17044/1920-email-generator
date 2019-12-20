import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Template} from '../model/template';
import {catchError, map} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({providedIn: 'root'})
export class TemplateService {
  constructor(private http: HttpClient) {
    httpOptions.headers =
      httpOptions.headers.set('Authorization', 'my-new-auth-token');
  }


  getTemplates(userId: string) {
    return this.http.get<Template[]>(`http://localhost:8080/templates/getUsersByIdentifier/${userId}`, httpOptions)
      .pipe(
      map(response => {
        return response;
      }));
  }

  createOrUpdateTemplate(userId: string, template: Template) {
    return this.http.post(`http://localhost:8080/templates/${userId}`, template, httpOptions);
  }

  deleteTemplate(templateId: string) {
    return this.http.delete(`http://localhost:8080/templates/${templateId}`);
  }
}


