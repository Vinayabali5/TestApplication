import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';

import { ApiConfigService } from '../api-config.service';
import { LegalSexService } from './legal-sex.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

describe('LegalSexService', () => {
    let service: LegalSexService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [
                HttpClientModule
            ]
        });
        service = TestBed.inject(LegalSexService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
