package controller;

public class LanguageController {
    //TODO add checkbox controller method
    //TODO get shared preferences set and draw countries accordingly
        //TODO add following pseudo code and set country map in shared preferences (?)

    //TODO make country data dynamic
    public String english = "[['Country', 'Language'], ['DZ', 7], ['AS', 80], ['AD', 22], ['AI', 92], ['AG', 80], ['AR ', 7], ['AW', 42], ['AU', 97], ['AT', 73], ['BS', 87], ['BD', 18], ['BB', 99], ['BE', 60], ['BZ', 82], ['BM', 97], ['BW', 38], ['BR', 5], ['BN', 39], ['BG', 25], ['CM', 38], ['CA ', 86], ['KY', 77], ['CL', 10], ['CN', 1], ['CO', 4], ['CK', 20], ['CR ', 8], ['HR ', 49], ['CY', 73], ['CZ', 27], ['DK', 86], ['DM', 94], ['EG', 35], ['EE', 50], ['FJ', 21], ['FI', 70], ['FM', 58], ['FR', 39], ['DE', 56], ['GH', 67], ['GI', 100], ['GR ', 51], ['GD', 91], ['GU ', 91], ['GY', 91], ['HK', 46], ['HU', 20], ['IN', 12], ['IQ', 35], ['IM', 100], ['IL', 85], ['IT', 34], ['JM', 98], ['JO', 45], ['KZ', 15], ['KE', 19], ['KI', 24], ['LV', 46], ['LB', 40], ['LS', 28], ['LR', 83], ['LT', 38], ['LU', 56], ['MG', 18], ['MW', 4], ['MY ', 63], ['MT', 89], ['MH', 98], ['MU', 16], ['MX', 13], ['MS', 68], ['MA', 14], ['MM', 4], ['NA ', 17], ['NR', 96], ['NP', 36], ['NL', 90], ['NZ', 98], ['NG', 53], ['MP', 83], ['NO', 90], ['PK', 57], ['PW', 93], ['PG', 50], ['PH', 64], ['PL', 37], ['PT', 27], ['PR', 49], ['IE', 98], ['RO', 31], ['RU', 5], ['RW', 15], ['KN', 78], ['LC', 43], ['VC', 95], ['WS', 50], ['SC', 38], ['SL', 84], ['SG', 83], ['SK', 26], ['SI', 59], ['SB', 32], ['ZA ', 31], ['ES', 22], ['LK', 47], ['SR', 87], ['SZ', 4], ['SE', 86], ['CH', 61], ['TZ', 10], ['TH ', 27], ['GM', 2], ['TO', 30], ['TT', 88], ['TR', 17], ['UG', 8], ['UA', 18], ['GB', 98], ['US', 95], ['VU', 84], ['YE', 9], ['ZM', 16], ['ZW', 42],]";


    /*
    Base array []

    Als checkbox clicked:
        krijg update aangeklikte talen //array als [english, french, german]
        clear ba/shared preferences

        voor taal nr 1
            add data
        voor taal nr 1...n
            voor land in taaldata
                als land in ba
                    als land value > ba value
                        overwrite ba land
                anders add land
    */
}
