let console = "0";

function calculate() {
    const consoleElement = document.getElementById('console');

    let firstChar = console.substr(0,1);
    let lastChar = console.substr(console.length - 1, console.length);

    //block
    let lastCharIsNotNumeric = (lastChar === "/" | lastChar === "*" | lastChar === "+" | lastChar === "-" | lastChar === ".");
    if (lastCharIsNotNumeric) return;

    let result = eval(console);

    consoleElement.innerHTML = result + '';
    console = result + '';
}

function typed(text) {
    const consoleElement = document.getElementById('console');

    let firstChar = console.substr(0,1);
    let lastChar = console.substr(console.length - 1, console.length);

    //block
    let anotherAction = (lastChar === "/" | lastChar === "*" | lastChar === "+" | lastChar === "-" | lastChar === ".") & (text === "/" | text === "*" | text === "+" | text === "-" | text === ".");
    if (anotherAction) return;
    if (text === '.' & (lastChar === "/" | lastChar === "*" | lastChar === "+" | lastChar === "-" | lastChar === ".")) return;


    //type
    if (firstChar === '0') {
        consoleElement.innerHTML = text;
        console = text;
    } else {
        consoleElement.innerHTML = console + text;
        console = console + text;
    }
    
}

function clean() {
    const consoleElement = document.getElementById('console');

    consoleElement.innerHTML = '0';
    console = '0';
}