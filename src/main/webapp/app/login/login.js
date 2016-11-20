/**
 * Created by NatanRamos on 06/10/2016.
 */

(function () {

    function loginController() {

        function _entrar() {
            $.post('api/utilitarios/login', $('form[rule=login]').serialize(), function (data) {
                if (data != 0) {
                    window.location.href = 'app/home/home.html';
                }
            });
        }

        function _validaForm() {
            var entrar = window.document.getElementById('btnEntrar');
            if (window.document.getElementById('usuario').value.trim() == '') {
                entrar.disabled = true;
                return;
            } else if (window.document.getElementById('senha').value.trim() == '') {
                entrar.disabled = true;
                return;
            }
            entrar.disabled = false;
        }

        return {
            entrar: _entrar,
            validaForm: _validaForm
        }
    }

    $(function () {
        window.ctrl = loginController();
        $('#btnEntrar').click(function () {
            ctrl.entrar();
        });
    });

})();