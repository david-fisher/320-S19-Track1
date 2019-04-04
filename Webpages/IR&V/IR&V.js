
new Vue({
    el: '#app',
    data: {
        icon: '\uf0a1',
        form : {
            firstName: '',
            lastName: '',
            addressLineOne: '',
            addressLineTwo: '',
            city: '',
            state: '',
            zipCode: '',
            phoneNumber: '',
            email: '',
            creditCardNumber: '',
            expiration: '',
            CVV: '',
            inquiry_type: '',
            logrocket_usecases: [],
            terms: false,
            concepts: [],
            js_awesome: ''
        },
        options: {
            inquiry: [
                { value: 'feature', text: "Feature Request"},
                { value: 'bug', text: "Bug Report"},
                { value: 'support', text: "Support"}
            ]
        }
    }
});