document.addEventListener('DOMContentLoaded', () => {
    // Foca no campo de usuário automaticamente
    const inp = document.getElementById('username');
    if (inp) inp.focus();

    // Desabilita o botão durante o envio para evitar duplo clique
    const form = document.querySelector('.login-form');
    const btn  = document.querySelector('.login-btn');
    if (form && btn) {
        form.addEventListener('submit', () => {
            btn.disabled  = true;
            btn.innerText = 'Entrando...';
        });
    }
});