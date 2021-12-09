import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';

import { ApiConfigService } from '../api-config.service';
import { SchoolService } from './school.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

describe('SchoolService', () => {
    let service: SchoolService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [
                HttpClientModule
            ]
        });
        service = TestBed.inject(SchoolService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
