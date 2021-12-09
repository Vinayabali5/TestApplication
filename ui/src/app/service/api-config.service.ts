import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { EnvWindow } from '../model/system/window';
import { ApplicationSettings } from '../model/system/application-settings';

export function configureApi(apiConfigService: ApiConfigService): () => Promise<any> {
    return () => apiConfigService.load();
}

@Injectable()
export class ApiConfigService {
    private DEBUG: boolean = false;

    private appConfig?: ApplicationSettings = undefined;

    constructor(private http: HttpClient) {}

    public load(): Promise<any> {
        console.debug('Loading config - api-config.service.ts');
        const currentWindow: EnvWindow = window as EnvWindow;
        this.appConfig = {
            apiUrl: currentWindow?.env?.apiUrl,
            debug:  currentWindow?.env?.debug
        }
        const output = new Promise<any>((resolve, reject) => {
            if (this.appConfig !== undefined) {
                resolve(this.appConfig);
            } else {
                reject("No configuration found.")
            }
        });

        return output;
    }

    get config(): ApplicationSettings | undefined {
        return this.appConfig;
    }

    getConfig(caller?: string): ApplicationSettings | undefined {
        if (this.DEBUG) {
            if (caller !== undefined) {
                console.log('Getting config info: ' + caller);
            } else {
                console.log('Getting config info');
            }
        }
        return this.config;
    }
}
