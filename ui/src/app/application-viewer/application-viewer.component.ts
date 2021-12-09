import { Component, OnInit } from '@angular/core';
import { Application } from '../model/application';
import { DataStore } from '../value-store';

@Component({
    selector: 'app-application-viewer',
    templateUrl: './application-viewer.component.html',
    styleUrls: ['./application-viewer.component.sass'],
})
export class ApplicationViewerComponent implements OnInit {
    constructor() {}

    ngOnInit(): void {}

    get application(): Application | null {
        const app = localStorage.getItem(DataStore.application);
        if (app !== null) {
            return JSON.parse(app) as Application;
        } else {
            console.log('An error occurred loading your application.');
            return null;
        }
    }
}
