import { Injectable } from '@angular/core';

export function configureApi(apiConfigService: MockApiConfigService): () => Promise<any> {
    return () => apiConfigService.load();
}

@Injectable()
export class MockApiConfigService {

    private appConfig = {
        apiUrl: '/api',
        debug: true
    };

    constructor() {}

    public load(): Promise<any> {
        const output = new Promise<any>((resolve, reject) => {
            if (this.appConfig !== undefined) {
                resolve(this.appConfig);
            } else {
                reject("No configuration found.")
            }
        });
        return output;
    }

    get config() {
        return this.appConfig;
    }

    getConfig(caller?: string) {
        return this.config;
    }
}
