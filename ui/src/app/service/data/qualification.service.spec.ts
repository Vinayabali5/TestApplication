import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';

import { ApiConfigService } from '../api-config.service';
import { QualificationService } from './qualification.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

describe('QualificationService', () => {
    let service: QualificationService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [
                HttpClientModule
            ]
        });
        service = TestBed.inject(QualificationService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
