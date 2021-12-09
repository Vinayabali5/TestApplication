export const environment = {
    production: true,
    apiUrl: window['env']['apiUrl'] as string || "api/",
    debug: window['env']['debug'] || false
};
