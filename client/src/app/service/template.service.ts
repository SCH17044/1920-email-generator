import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Template} from '../model/template';
import {map} from 'rxjs/operators';

@Injectable({providedIn: 'root'})
export class TemplateService {
  constructor(private http: HttpClient) {}

  getTemplates(userId: string) {
    return this.http.get<Template[]>(`http://localhost:8080/templates/getUsersByIdentifier/${userId}`, {
      observe: 'response'})
      .pipe(
      map(response => {
        return response.body;
      }));
  }
}
