import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { ApiConfigService } from '../api-config.service';
import { BasicApiService } from './basic-api.service';

import { Course } from '../../model/course';
import { LookupDataService } from './lookup-data-service';

@Injectable({
    providedIn: 'root',
})
export class CourseService extends BasicApiService implements LookupDataService<Course[]> {
    private baseUrl: string = '/courses';
    private url: string;

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient
    ) {
        super(config, http);
        this.url = this.apiBaseUrl + this.baseUrl;
        this.log(`url set to ${this.url}`, 'CourseService');
    }

    getAll(): Observable<Course[]> {
        return this.http.get<Course[]>(this.url).pipe(
            tap((_) => this.log('fetched all', 'CourseService')),
            catchError(this.handleError<Course[]>('getAll', []))
        );
    }
}
