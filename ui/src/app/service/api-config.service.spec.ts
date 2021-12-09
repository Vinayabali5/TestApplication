import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';

import { ApiConfigService } from './api-config.service';
import { MockApiConfigService } from 'src/testing/mock-api-config.service';

describe('ApiConfigService', () => {
    let service: ApiConfigService;

    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [
                { provide: ApiConfigService, useClass: MockApiConfigService }
            ],
            imports: [HttpClientModule]
        });
        service = TestBed.inject(ApiConfigService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });

    it('should have a configuration object', () => {
        service.load();
        expect(service.config).toBeTruthy();
    });
});
