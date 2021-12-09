import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

import { ApiConfigService } from '../api-config.service';
import { BasicApiService } from './basic-api.service';

import { ContactType } from '../../model/contact-type';
import { LookupDataService } from './lookup-data-service';

@Injectable({
    providedIn: 'root',
})
export class ContactTypeService extends BasicApiService implements LookupDataService<ContactType[]> {
    private baseUrl: string = '/contact-types';
    private url: string;

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient
    ) {
        super(config, http);
        this.url = this.apiBaseUrl + this.baseUrl;
        this.log(`url set to ${this.url}`, 'ContactTypeService');
    }

    getAll(): Observable<ContactType[]> {
        return this.http.get<ContactType[]>(this.url).pipe(
            tap((_) => this.log('fetched all', 'ContactTypeService')),
            catchError(this.handleError<ContactType[]>('getContactTypes', []))
        );
    }
}
