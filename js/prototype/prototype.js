function Person(name, age, gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
}

var person1 = new Person('Jonatan', 24, 'male');

console.log(person1)
console.log(person1.__proto__)

var person2 = Object.create(person1);

console.log(person2)
console.log(person2.__proto__)

var person3 = new person1.constructor('Bruna', 22, 'female');

console.log(person3)
console.log(person3.__proto__)


function PersonFisic(cpf) {
    this.cpf
}

PersonFisic.prototype = new Person();
PersonFisic.prototype.constructor = PersonFisic;


// OU

class Pessoa { 
    constructor(name) { 
        this.name = name; 
    } 
} 

class PessoaFisica extends Pessoa { 
    setCPF(cpf) { 
        this.cpf += this.cpf; 
    } 
}

class PessoaJuridica extends Pessoa { 
    setCNPJ(cnpj) { 
        this.cnpj += this.cnpj; 
    } 
}

var pessoa = new PessoaFisica('Jonatan');
pessoa.setCPF("000.000.000-00");

console.log("pessoa");
console.log(pessoa);