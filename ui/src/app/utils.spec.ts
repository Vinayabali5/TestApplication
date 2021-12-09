import { LoqateLookupValue } from "./model/loqate/lookup-value";
import { School } from "./model/school";
import { Utils } from "./utils";

describe('Utils', () => {

    describe('cleanSchoolName', () => {
        it('should remove a leading "The " for the name of the school', () => {
            let testName = 'The Warwick';
            let result = Utils.cleanSchoolName(testName);
            expect(result).toBe('Warwick');
        });

        it('should  not change a school name with if no alterations are required', () => {
            let testName = 'Oakwood';
            let result = Utils.cleanSchoolName(testName);
            expect(result).toEqual(testName);
        });
    });

    describe('sortSchool', () => {
        it('should sort an array of School objects', () => {
            let school1: School = {
                id: 10,
                name: 'ABC School',
                address: '',
                partner: false
            }, school2: School = {
                id: 20,
                name: 'DEF School',
                address: '',
                partner: false
            }, school3: School = {
                id: 20,
                name: 'HIJ School',
                address: '',
                partner: false
            }

            let schools: School[] = [school2, school3, school1];

            expect(schools[0]).toEqual(school2);
            expect(schools[1]).toEqual(school3);
            expect(schools[2]).toEqual(school1);

            let sortedSchool = schools.sort(Utils.sortSchools)

            expect(sortedSchool[0]).toEqual(school1);
            expect(sortedSchool[1]).toEqual(school2);
            expect(sortedSchool[2]).toEqual(school3);

        });
    });

    describe('sortAddressLookup', () => {
        it('should sort a list of LoqateLookupValue objects', () => {
            let address1: LoqateLookupValue = {
                Id: 'TEST-ID1',
                Text: '123 Street Street'
            }, address2: LoqateLookupValue = {
                Id: 'TEST-ID2',
                Text: '456 Street Street'
            }, address3: LoqateLookupValue = {
                Id: 'TEST-ID3',
                Text: '789 Street Street'
            }

            let addressList = [address3, address1, address2];

            expect(addressList[0]).toEqual(address3);
            expect(addressList[0]).toEqual(address3);
            expect(addressList[0]).toEqual(address3);

            let sortedAddressList = addressList.sort(Utils.sortAddressLookup);

            expect(sortedAddressList[0]).toEqual(address1);
            expect(sortedAddressList[1]).toEqual(address2);
            expect(sortedAddressList[2]).toEqual(address3);

        });
    });


});
