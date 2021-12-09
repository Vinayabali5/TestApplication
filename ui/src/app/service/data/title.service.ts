import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { ApiConfigService } from '../api-config.service';
import { BasicApiService } from './basic-api.service';
import { LookupDataService } from './lookup-data-service';
import { Title } from '../../model/title';

@Injectable({
    providedIn: 'root',
})
export class TitleService extends BasicApiService implements LookupDataService<Title[]> {
    private baseUrl: string = '/titles';
    private url: string;

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient
    ) {
        super(config, http);
        this.url = this.apiBaseUrl + this.baseUrl;
        this.log(`url set to ${this.url}`, 'TitleService');
    }

    getAll(): Observable<Title[]> {
        return this.http.get<Title[]>(this.url).pipe(
            tap((_) => this.log('fetched all', 'TitleService')),
            catchError(this.handleError<Title[]>('getAll', []))
        );
    }
}
