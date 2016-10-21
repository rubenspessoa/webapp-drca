import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';

import {DisciplineService} from '../services/discipline.service';
import {Discipline} from '../services/discipline';

import {StudentAttendsDiscipline} from '../services/studentAttendsDiscipline';
import {StudentAttendsDisciplineService} from '../services/studentAttendsDiscipline.service';

import {ProfessorService} from '../services/professor.service';
import {Professor} from '../services/professor';

import {StudentService} from '../services/student.service';
import {Student} from '../services/student';

@Component({
  selector: 'fountain-discipline',
  template: require('../templates/discipline.component.html')
})

export class DisciplineComponent implements OnInit {
  discipline: any;
  professor: any;
  preRequisites: any[];
  studiscs = [];
  students = [];
  public text: string;

  constructor(
    private route: ActivatedRoute,
    private disciplineService: DisciplineService,
    private professorService: ProfessorService,
    private studentService: StudentService
  ) {
    this.text = 'Pauta da Disciplina: ';
  }

  ngOnInit(): void {
    this.route.params.forEach(
      (params: Params) => {
        let id = params['id'];
        console.log(id);

        this.disciplineService.getDiscipline(id).then( disc => {
          this.discipline = disc;
          console.log(disc);
          if(typeof disc.requiredClasses != 'undefined') {
            console.log(disc.requiredClasses);
            this.preRequisites = [];
            console.log(disc.requiredClasses);
            disc.requiredClasses.forEach(
              preRequisite => {
                this.disciplineService.getDiscipline(preRequisite).then(id => {
                  this.preRequisites.push(id);
                });
              }
            );
          }

          this.professorService.getProfessor(disc.teacher.objectId).then(professor => {
            this.professor = professor;
            console.log(this.professor);
          });

          this.studentService.getStudentsForClassWithId(disc.objectId).then(students => {
            this.students = students;
            console.log(students);
          });
        });

        /*
        this.getDiscipline(id)
          .then(
            discipline => this.getStuDiscs(this.discipline.id)
              .then(studiscs => this.getStudents())
          );
        */
      }
    );
  }

/*
  getDiscipline(id: number): Promise<Discipline> {
      return this.disciplineService.getDiscipline(id)
        .then(discipline => this.discipline = discipline);
  }

  getStuDiscs(disciplineId: number): Promise<StudentAttendsDiscipline[]> {
    return this.studiscService.getStudentsBy(disciplineId)
      .then(studiscs => this.studiscs = studiscs);
  }

  getStudents(): void {
    this.studiscs.forEach(
      studisc => this.getStudent(studisc.studentId));
  }

  getStudent(id: number): void {
    this.studentService.getStudent(id)
    .then(student => this.students.push(student));
  }
  */
}
