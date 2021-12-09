import { Address } from './address';
import { Contact } from './contact';
import { Course } from './course';

export interface Application {

    id: number;

    // Personal Details
    legalFirstName: string;
    middleNames: string;
    legalSurname: string;
    preferredName: string;
    previousSurname: string;
    dateOfBirth: Date;
    legalSex: number;
    _legalSexDescription: string;
    gender: number;
    _genderDescription: string;
    home: string;
    mobile: string;
    email: string;
    nationality: number;
    _nationalityDescription: string;
    resident: boolean;

    // Sibling Details
    sibling: boolean;
    siblingAdmNo: string;
    siblingName: string;
    siblingYear: number;

    // Address Details
    address: Address;

    // Contact Details
    contacts: Contact[];

    // School Details
    school: number;
    _schoolDescription: string;
    endDate: Date;

    // Extra School Details
    schoolNotListed: boolean;
    schoolName: string;
    schoolAddress: Address;

    // Course Selection
    courses: Course[];

    // Other Fields
    received: Date;
}

