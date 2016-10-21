import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import { ip } from './rest';

import { Professor } from './professor';
import { PROFESSORS } from './mock-professors';

@Injectable()
export class ProfessorService {

  private professorUrl = 'http://' + ip + ':8080/fetchTeacherWithId?id=';  // URL to web api

  constructor(
    private http: Http
  ) {}

  getProfessor(id: string): Promise<any> {
    let url = this.professorUrl + id;
    console.log(url);
    return this.http.get(url)
               .toPromise()
               .then(response => response.json().result);
  }

  getProfessors(): Promise<Professor[]> {
    return Promise.resolve(PROFESSORS);
  }
}
