import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';

import { ApiConfigService } from '../api-config.service';
import { GenderService } from './gender.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

describe('GenderService', () => {
    let service: GenderService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [
                HttpClientModule
            ]
        });
        service = TestBed.inject(GenderService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
