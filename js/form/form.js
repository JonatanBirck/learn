function validaMascaraCPF(cpf){	
	var cpfValido = /^(([0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2})|([0-9]{11}))$/;	 
	return cpfValido.test(cpf) == true;
}

function fMasc(objeto,mascara) {
    obj=objeto
    masc=mascara
    setTimeout("fMascEx()",1)
}

function tMasc(objeto,mascara) {
    obj=objeto
    masc=mascara
    setTimeout("tMascEx()",1)
}

function fMascEx() {
    obj.value=masc(obj.value)
}

function tMascEx() {
    obj.value=masc(obj.value)
}

function mTelefone(telefone){
    telefone=telefone.replace(/\D/g, '');
    return telefone
}

function mCPF(cpf){
    cpf=cpf.replace(/\D/g,"")
    cpf=cpf.replace(/(\d{3})(\d)/,"$1.$2")
    cpf=cpf.replace(/(\d{3})(\d)/,"$1.$2")
    cpf=cpf.replace(/(\d{3})(\d{1,2})$/,"$1-$2")
    return cpf
}

function testaCPF(strCPF) {

    if(strCPF === '') return true;

    var Soma;
    var Resto;
    Soma = 0;
  if (strCPF == "00000000000") return false;

  for (i=1; i<=9; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (11 - i);
  Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11))  Resto = 0;
    if (Resto != parseInt(strCPF.substring(9, 10)) ) return false;

  Soma = 0;
    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i-1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11))  Resto = 0;
    if (Resto != parseInt(strCPF.substring(10, 11) ) ) return false;
    return true;
}

function submit() {
    let name = document.getElementById("name").value; 
    let email = document.getElementById("mail").value; 
    let cpf = document.getElementById("cpf").value; 
    let phone = document.getElementById("phone").value; 
    let msg = document.getElementById("msg").value; 

    if (!testaCPF(cpf)) {
        alert("CPF não é valido: " + cpf);
    } else {
        alert("Nome: " + name + "\n" + "Email: " + email + "\n" + "CPF: " + cpf + "\n" + "Telefone: " + phone + "\n" + "Mensagem: " + msg);
    }

}

    