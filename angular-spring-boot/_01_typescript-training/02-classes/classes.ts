console.log("start");
console.log("##############################################");

class Customer {
    _firstName: string | undefined;
    _lastName: string | undefined;
}

class Customer2 {
    private _firstName: string;
    private _lastName: string;

    constructor(firstName: string, lastName: string) {
        this._firstName = firstName;
        this._lastName = lastName;
    }

    public get firstName(): string {
        return this._firstName;
    }

    public set firstName(firstName: string) {
        this._firstName = firstName;
    }

    public get lastName(): string {
        return this._lastName;
    }

    public set lastName(lastName: string) {
        this._lastName = lastName;
    }
}

class Customer3 {
    constructor(private _firstName: string, private _lastName: string) { }

    public get firstName(): string {
        return this._firstName;
    }

    public get lastName(): string {
        return this._lastName;
    }
}

let myCustomer = new Customer();

myCustomer._firstName = "Mario";
myCustomer._lastName = "Calzone";
console.log(`customer1: ${myCustomer._firstName} ${myCustomer._lastName}`);

let myCustomer2 = new Customer2("Luigi", "Bergamo");
console.log("Customer2: " + myCustomer2.firstName + " " + myCustomer2.lastName);

let myCustomer3 = new Customer3("Aurora", "Borealis");
console.log(`customer3: ${myCustomer3.firstName} ${myCustomer3.lastName} ` )


console.log("##############################################");
console.log("end");

