/**
 * Created by NatanRamos on 15/10/2016.
 */

(function () {

    function Pessoas(id, nome, tipo_pessoa, documento, data_nascimento, telefone, email, rua, numero, id_municipios, id_estados) {
        this.id = id;
        this.nome = nome;
        this.tipoPessoa = tipo_pessoa;
        this.documento = documento;
        this.dataNascimento = data_nascimento;
        this.telefone = telefone;
        this.email = email;
        this.rua = rua;
        this.numero = numero;
        this.idMunicipios = id_municipios;
        this.idEstados = id_estados;
    }

    function pessoasController() {

        var modeloLinhaTabela;

        function _carregar() {
            $.getJSON('../../api/pessoas', function (data) {
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
            _preencheForm(new Pessoas());
        }

        function _validaForm() {
            if (window.document.getElementById('descricao').value.trim() == '') {
                alert('Informe a descrição!');
                window.document.getElementById('descricao').focus();
                return false;
            } else if (window.document.getElementById('dataInicial').value.trim() == '') {
                alert('Informe a data de início!');
                window.document.getElementById('dataInicial').focus();
                return false;
            } else if (window.document.getElementById('dataFinal').value.trim() == '') {
                alert('Informe a data de término!');
                window.document.getElementById('dataFinal').focus();
                return false;
            } else if (window.document.getElementById('dataVencimento').value.trim() == '') {
                alert('Informe a data de vencimento!');
                window.document.getElementById('dataVencimento').focus();
                return false;
            }
            return true;
        }

        function _salvar() {
            if (_validaForm()) {
                $.post('../../api/pessoas', $('form[rule=cadastro]').serialize(), function () {
                    $('#myModal').modal('hide');
                    _carregar();
                });
            }
        }

        function _remover(id) {
            $.post('../../api/pessoas/remover', {id: id} ,function () {
                _carregar();
            });
        }

        function _editar(id) {
            $.getJSON('../../api/pessoas', 'id=' + id, function (data) {
                _preencheForm(data);
            })
        }

        function _formataData(data) {
            if (data == 'null') {
                return '';
            }
            return data.substr(8,2) + '/' + data.substr(5,2) + '/' + data.substr(0,4);
        }

        function _formataDocumento(tipoPessoa, documento) {
            if (tipoPessoa == 'F') {
                return documento.substr(0,3) + '.' + documento.substr(3,3) + '.' + documento.substr(6,3) + '-' + documento.substr(9,2);
            } else if (tipoPessoa == 'J') {
                return documento.substr(0,2) + '.' + documento.substr(2,3) + '.' + documento.substr(5,3) + '/' + documento.substr(8,4) + '-' + documento.substr(12,2);
            }
            return documento;
        }

        function _renderTable(data) {
            var final = '';
            modeloLinhaTabela = modeloLinhaTabela || $('table.table tbody').html();
            for (var i = 0; i < data.length; i++) {
                var res = modeloLinhaTabela;
                var linha = data[i];
                res = res.replace(/\{\{ID\}\}/g, linha.id);
                res = res.replace(/\{\{NOME\}\}/g, linha.nome);
                res = res.replace(/\{\{TIPO_PESSOA\}\}/g, linha.tipoPessoa == 'J' ? 'Jurídica': 'Física');
                res = res.replace(/\{\{DOCUMENTO\}\}/g, _formataDocumento(linha.tipoPessoa, linha.documento));
                res = res.replace(/\{\{DATA_NASCIMENTO\}\}/g, _formataData(linha.dataNascimento));
                res = res.replace(/\{\{TELEFONE\}\}/g, linha.telefone);
                res = res.replace(/\{\{EMAIL\}\}/g, linha.email);
                final += res;
            }
            $('table.table tbody').html(final);
        }

        return {
            add: _adicionar,
            edit: _editar,
            save: _salvar,
            remove: _remover
        }
    }

    $(function () {
        window.ctrl = pessoasController();
    });

})();