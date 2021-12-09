import { LoqateLookupValue } from "./model/loqate/lookup-value";
import { School } from "./model/school";

export class Utils {

    /**
     * This method is used to clean a school's name prior to sorting.
     *
     * @param name the name of the school to clean.
     * @returns a clean version of the school name.
     */
    static cleanSchoolName(name: string): string {
        if (name === undefined || name === null) return name;
        if (name.toUpperCase().startsWith('THE ')) {
            name = name.substring(4);
        }
        return name;
    }

    /**
     * This method is used to sort an array of school object by their name.
     */
    static sortSchools(a: School, b: School): number {
        if (a === undefined || a === null) return 1;
        if (b === undefined || b === null) return -1;
        let first: string = Utils.cleanSchoolName(a.name),
            second: string = Utils.cleanSchoolName(b.name);
        if (first === undefined || first === null) return 1;
        if (second === undefined || second === null) return -1;

        if (a.partner == true && b.partner == false) {
            return -1;
        } else if (a.partner == false && b.partner == true) {
            return 1;
        } else {
            if (first.toUpperCase() <= second.toUpperCase()) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }

    /**
     * This method is used to sort an array of LoqateLookupValues.
     */
    static sortAddressLookup(a: LoqateLookupValue, b: LoqateLookupValue) {
        return (a.Text > b.Text) ? 1 : -1;
    }



}
