import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { ApiConfigService } from '../api-config.service';
import { BasicApiService } from './basic-api.service';

import { QualificationType } from '../../model/qualification-type';
import { LookupDataService } from './lookup-data-service';

@Injectable({
    providedIn: 'root',
})
export class QualificationTypeService extends BasicApiService implements LookupDataService<QualificationType[]> {
    private baseUrl: string = '/qualification-types';
    private url: string;

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient
    ) {
        super(config, http);
        this.url = this.apiBaseUrl + this.baseUrl;
        this.log(`url set to ${this.url}`, 'QualificationTypeService');
    }

    getAll(): Observable<QualificationType[]> {
        return this.http.get<QualificationType[]>(this.url).pipe(
            tap((_) => this.log('fetched all', 'QualificationTypeService')),
            catchError(this.handleError<QualificationType[]>('getAll', []))
        );
    }
}
