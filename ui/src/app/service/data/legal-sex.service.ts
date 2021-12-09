import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { ApiConfigService } from '../api-config.service';
import { BasicApiService } from './basic-api.service';

import { LegalSex } from '../../model/legal-sex';
import { LookupDataService } from './lookup-data-service';

@Injectable({
    providedIn: 'root',
})
export class LegalSexService extends BasicApiService implements LookupDataService<LegalSex[]> {
    private baseUrl: string = '/legal-sexes';
    private url: string;

    constructor(
        protected config: ApiConfigService,
        protected http: HttpClient
    ) {
        super(config, http);
        this.url = this.apiBaseUrl + this.baseUrl;
        this.log(`url set to ${this.url}`, 'LegalSexService');
    }

    getAll(): Observable<LegalSex[]> {
        return this.http.get<LegalSex[]>(this.url).pipe(
            tap((_) => this.log('fetched all', 'LegalSexService')),
            catchError(this.handleError<LegalSex[]>('getAll', []))
        );
    }
}
