export abstract class RegEx {

    /**
     * This regex is to validate that a date is in the format DD/MM/YYYY.
     */
    public static date = /^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d$/;

    /**
     * This regex is to full validate a date field in the UK DD/MM/YYYY format. This should check that day/month combinations are valid.
     */
    public static dateFull = /(^(((0[1-9]|1[0-9]|2[0-8])[\/](0[1-9]|1[012]))|((29|30|31)[\/](0[13578]|1[02]))|((29|30)[\/](0[4,6,9]|11)))[\/](19|[2-9][0-9])\d\d$)|(^29[\/]02[\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;

    /**
     * This regex is a very basic validation ensuring that all characters are numeric or the plus symbol.
     */
    public static telephone = /^\+?[0-9]{11,15}$/;

    /**
     * This regex is used to validate a telephone number in EPP format.
     *
     * @see https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s03.html
     */
    public static telephoneEPP = /^\+[0-9]{1,3}\.[0-9]{4,14}(?:x.+)?$/;

    /**
     * This regex is used to validate a telephone number in international format.
     *
     * @see https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s03.html
     */
    public static telephoneInternational = /^\+(?:[0-9] ?){6,14}[0-9]$/;

    /**
     * This regex is used to validate the input of an email address. The justification for using this regex can be found
     * at the attached link, this describes the various trade-offs for using this regex. Note that this has been modified
     * from the example in the link to include the lowercase a-z characters.
     *
     * @see https://www.regular-expressions.info/email.html
     */
    public static email = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

    /**
     * This regex is used to validate the input of a year field.
     */
    public static year = /^[12][90][0-9]{2}$/;

    /**
     * This regex is used to validate the input of a student admission number / student ID.
     */
    public static admNo = /^[12][0-9]{5}$/;

}
