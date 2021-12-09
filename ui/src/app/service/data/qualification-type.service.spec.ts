import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';

import { ApiConfigService } from '../api-config.service';
import { QualificationTypeService } from './qualification-type.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

describe('QualificationTypeService', () => {
    let service: QualificationTypeService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [
                HttpClientModule
            ]
        });
        service = TestBed.inject(QualificationTypeService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
