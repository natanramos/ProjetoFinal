/**
 * Created by NatanRamos on 13/10/2016.
 */

(function () {

    function Competencias(id, descricao, data_inicial, data_final, data_vencimento) {
        this.id = id;
        this.descricao = descricao;
        this.dataInicial = data_inicial;
        this.dataFinal = data_final;
        this.dataVencimento = data_vencimento;
    }

    function competenciasController() {

        var modeloLinhaTabela;

        function _carregar() {
            $.getJSON('../../api/competencias', function (data) {
                _renderTable(data);
            });
        }

        _carregar();

        function _preencheForm(registro) {
            $('input[name=id]').val(registro.id);
            $('input[name=descricao]').val(registro.descricao);
            $('input[name=dataInicial]').val(registro.dataInicial);
            $('input[name=dataFinal]').val(registro.dataFinal);
            $('input[name=dataVencimento]').val(registro.dataVencimento);
        }

        function _adicionar() {
            window.document.getElementById('btnSalvar').disabled = true;
            _preencheForm(new Competencias());
        }

        function _validaForm() {
            var salvar = window.document.getElementById('btnSalvar');
            if (window.document.getElementById('descricao').value.trim() == '') {
                salvar.disabled = true;
                return;
            } else if (window.document.getElementById('dataInicial').value.trim() == '') {
                salvar.disabled = true;
                return;
            } else if (window.document.getElementById('dataFinal').value.trim() == '') {
                salvar.disabled = true;
                return;
            } else if (window.document.getElementById('dataVencimento').value.trim() == '') {
                salvar.disabled = true;
                return;
            }
            salvar.disabled = false;
        }

        function _salvar() {
            $.post('../../api/competencias', $('form[rule=cadastro]').serialize(), function () {
                $('#myModal').modal('hide');
                _carregar();
            });
        }

        function _remover(id) {
            $.post('../../api/competencias/remover', {id: id} ,function () {
                _carregar();
            });
        }

        function _editar(id) {
            $.getJSON('../../api/competencias', 'id=' + id, function (data) {
                _preencheForm(data);
                window.document.getElementById('btnSalvar').disabled = false;
            })
        }

        function _formataData(data) {
            return data.substr(8,2) + '/' + data.substr(5,2) + '/' + data.substr(0,4);
        }

        function _renderTable(data) {
            var final = '';
            modeloLinhaTabela = modeloLinhaTabela || $('table.table tbody').html();
            for (var i = 0; i < data.length; i++) {
                var res = modeloLinhaTabela;
                var linha = data[i];
                res = res.replace(/\{\{ID\}\}/g, linha.id);
                res = res.replace(/\{\{DESCRICAO\}\}/g, linha.descricao);
                res = res.replace(/\{\{DATA_INICIAL\}\}/g, _formataData(linha.dataInicial));
                res = res.replace(/\{\{DATA_FINAL\}\}/g, _formataData(linha.dataFinal));
                res = res.replace(/\{\{DATA_VENCIMENTO\}\}/g, _formataData(linha.dataVencimento));
                final += res;
            }
            $('table.table tbody').html(final);
        }

        return {
            add: _adicionar,
            edit: _editar,
            save: _salvar,
            remove: _remover,
            validaForm: _validaForm
        }
    }

    $(function () {
        window.ctrl = competenciasController();
    });

})();