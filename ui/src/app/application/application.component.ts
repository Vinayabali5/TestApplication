import {
    Component,
    OnInit
} from '@angular/core';
import {
    FormBuilder,
    FormGroup
} from '@angular/forms';
import { Router } from '@angular/router';
import {
    ApplicationService
} from '../service/data/application.service';

@Component({
    selector: 'application-form',
    templateUrl: './application.component.html',
    styleUrls: ['./application.component.sass']
})
export class ApplicationComponent implements OnInit {

    DEBUG: boolean = false;

    application: FormGroup;

    message?: string;

    constructor(private service: ApplicationService, private fb: FormBuilder, private router: Router) {
        this.application = this.fb.group({
            id: [null],
            personalDetails: [''],
            contactDetails: [''],
            schoolDetails: [''],
            courseSelection: ['']
        });
    }

    ngOnInit(): void {}

    createApplication(): void {
        // Retrieve data from the form
        const personalDetails = this.application.get('personalDetails')?.value;
        const contactDetails = this.application.get('contactDetails')?.value;
        const schoolDetails = this.application.get('schoolDetails')?.value;
        const courseSelection = this.application.get('courseSelection')?.value;

        // Collate data for course selection
        const courseList: object[] = [];
        for (const course of courseSelection) {
            courseList.push({
                courseId: course.id
            });
        }

        // Create data to be sent to the API
        const data = Object.assign(personalDetails, contactDetails, schoolDetails);
        data.courses = courseList;

        // Send data to the API
        this.service.save(data).subscribe({
            next: res => {
                    this.router.navigate(['application-confirmation']);                                                
            },
            error: err => {
                    console.log("Application Unsuccessful");    
                    var debugData = JSON.stringify(data);
                    this.service.debug(debugData).subscribe({
                        next: res => {
                            this.router.navigate(['application-failed']); 
                        }
                    });                                 
            }
        });
    }

    debugOutput(): void {
        const personalDetails = (this.application.controls['personalDetails'] as FormGroup).controls;
        const contactDetails = this.application.get('contactDetails');
        const schoolDetails = this.application.get('schoolDetails');
        const courseSelection = this.application.get('courseSelection');

        const output = {
            applicationFormGroup: this.application,
            personalDetailsForm: personalDetails,
            contactDetailsForm: contactDetails,
            schoolDetailsForm: schoolDetails,
            courseSelectionForm: courseSelection
        };

        console.log(output);

//        var data = JSON.stringify(output);
    }

    isValid(): boolean | undefined {
        const personalDetailsValid = this.application.get('personalDetails')?.valid;
        const contactDetailsValid = this.application.get('contactDetails')?.valid;
        const schoolDetailsValid = this.application.get('schoolDetails')?.valid;
        const courseSelectionValid = this.application.get('courseSelection')?.valid;
        return personalDetailsValid && contactDetailsValid && schoolDetailsValid && courseSelectionValid;
    }

}
