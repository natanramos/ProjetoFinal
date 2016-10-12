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

        return {
            entrar: _entrar
        }
    }

    $(function () {
        window.ctrl = loginController();
        $('#btnEntrar').click(function () {
            ctrl.entrar();
        });
    });

})();