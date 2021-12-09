import { HttpClient, HttpClientModule, HttpHandler } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

import { ApiConfigService } from '../api-config.service';
import { ApplicationService } from './application.service';

describe('ApplicationService', () => {
    let service: ApplicationService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [
                HttpClientModule
            ]
        });
        service = TestBed.inject(ApplicationService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
