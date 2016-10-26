/**
 * Created by NatanRamos on 21/10/2016.
 */

(function () {

    function Controles(id, mensalista, id_pessoas, placa, marca, modelo, cor, responsavel, data_hora_entrada, data_hora_saida, situacao, valor) {
        this.id = id;
        this.mensalista = mensalista;
        this.idPessoas = id_pessoas;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.responsavel = responsavel;
        this.dataHoraEntrada = data_hora_entrada;
        this.dataHoraSaida = data_hora_saida;
        this.situacao = situacao;
        this.valor = valor;
    }

    function controlesController() {

        var modeloLinhaTabela;

        function _carregar() {
            //$('#tipoPessoa option[value=""]').attr({ selected : "selected" });
            $.getJSON('../../api/controles', function (data) {
                _renderTable(data);
            });
            $.getJSON('../../api/pessoas', function (data) {
                _renderPessoas(data);
                $('#idPessoas option[value=""]').attr({ selected : "selected" });
            });
        }

        _carregar();

        function _preencheForm(registro) {
            $('input[name=id]').val(registro.id);
            $('select[name=mensalista]').val(registro.mensalista);
            $('select[name=idPessoas]').val(registro.idPessoas);
            $('input[name=placa]').val(registro.placa);
            $('select[name=marca]').val(registro.marca);
            $('input[name=modelo]').val(registro.modelo);
            $('input[name=cor]').val(registro.cor == 'null' ? '' : registro.cor);
            $('input[name=responsavel]').val(registro.responsavel == 'null' ? '' : registro.responsavel);
            $('input[name=dataHoraEntrada]').val(registro.dataHoraEntrada);
            $('input[name=dataHoraSaida]').val(registro.dataHoraSaida);
            $('select[name=situacao]').val(registro.situacao);
            $('input[name=valor]').val(registro.valor);
        }

        function _adicionar() {
            _preencheForm(new Pessoas());
        }

        function _validaForm() {
            if (window.document.getElementById('nome').value.trim() == '') {
                alert('Informe o nome!');
                window.document.getElementById('nome').focus();
                return false;
            } else if (window.document.getElementById('tipoPessoa').value.trim() == '') {
                alert('Informe o tipo!');
                window.document.getElementById('tipoPessoa').focus();
                return false;
            } else if (window.document.getElementById('documento').value.trim() == '') {
                alert('Informe o CPF/CNPJ!');
                window.document.getElementById('documento').focus();
                return false;
            } else if (window.document.getElementById('telefone').value.trim() == '') {
                alert('Informe o telefone!');
                window.document.getElementById('telefone').focus();
                return false;
            }
            return true;
        }

        function _salvar() {
            if (_validaForm()) {
                $.post('../../api/controles', $('form[rule=cadastro]').serialize(), function () {
                    $('#myModal').modal('hide');
                    _carregar();
                });
            }
        }

        function _remover(id) {
            $.post('../../api/controles/remover', {id: id} ,function () {
                _carregar();
            });
        }

        function _editar(id) {
            $.getJSON('../../api/controles', 'id=' + id, function (data) {
                _changeCampos(data.tipoPessoa);
                _preencheForm(data);
            })
        }

        function _formataData(data) {
            if (data == 'null') {
                return '';
            }
            return data.substr(8,2) + '/' + data.substr(5,2) + '/' + data.substr(0,4);
        }

        function _formataSituacao(situacao) {
            if (situacao == 'A') {
                return 'Aberto'
            }
            return '';
        }

        function _renderTable(data) {
            var final = '';
            modeloLinhaTabela = modeloLinhaTabela || $('table.table tbody').html();
            for (var i = 0; i < data.length; i++) {
                var res = modeloLinhaTabela;
                var linha = data[i];
                res = res.replace(/\{\{ID\}\}/g, linha.id);
                res = res.replace(/\{\{MENSALISTA\}\}/g, linha.mensalista == 'S' ? 'Sim': 'Não');
                res = res.replace(/\{\{PESSOA\}\}/g, linha.pessoa.nome);
                res = res.replace(/\{\{PLACA\}\}/g, linha.placa);
                res = res.replace(/\{\{MODELO\}\}/g, linha.modelo == 'null' ? '' : linha.modelo);
                res = res.replace(/\{\{DATA_HORA_ENTRADA\}\}/g, _formataData(linha.dataHoraEntrada));
                res = res.replace(/\{\{DATA_HORA_SAIDA\}\}/g, _formataData(linha.dataHoraSaida));
                res = res.replace(/\{\{RESPONSAVEL\}\}/g, linha.responsavel == 'null' ? '' : linha.responsavel);
                res = res.replace(/\{\{VALOR\}\}/g, linha.valor);
                res = res.replace(/\{\{SITUACAO\}\}/g, _formataSituacao(linha.situacao));
                final += res;
            }
            $('table.table tbody').html(final);
        }

        function _renderPessoas(data) {
            var options = '<option value=""></option>';
            data.forEach(function (pessoa) {
                options += '<option value="' + pessoa.id + '">' + pessoa.nome + '</option>';
            });
            $("#idPessoas").html(options);
        }

        function _changeCampos(tipoPessoa) {
            var dataNascimento = window.document.getElementById('dataNascimento');
            var documento = window.document.getElementById('documento');
            if (tipoPessoa == '') {
                dataNascimento.value = '';
                documento.value = '';
                dataNascimento.disabled = true;
                documento.disabled = true;
                return;
            }
            if (tipoPessoa == 'F') {
                dataNascimento.disabled = false;
                documento.disabled = false;
                var im = new Inputmask("999.999.999-99");
                im.mask(documento);
                return;
            }
            if (tipoPessoa == 'J') {
                dataNascimento.disabled = true;
                documento.disabled = false;
                var im = new Inputmask("99.999.999/9999-99");
                im.mask(documento);
                return;
            }
        }

        function _changeTipoPessoa() {
            var tipoPessoa = window.document.getElementById('tipoPessoa').value;
            var dataNascimento = window.document.getElementById('dataNascimento');
            var documento = window.document.getElementById('documento');
            if (tipoPessoa == '') {
                dataNascimento.value = '';
                documento.value = '';
                dataNascimento.disabled = true;
                documento.disabled = true;
                return;
            }
            if (tipoPessoa == 'F') {
                dataNascimento.disabled = false;
                documento.disabled = false;
                dataNascimento.value = '';
                documento.value = '';
                var im = new Inputmask("999.999.999-99");
                im.mask(documento);
                return;
            }
            if (tipoPessoa == 'J') {
                dataNascimento.disabled = true;
                documento.disabled = false;
                dataNascimento.value = '';
                documento.value = '';
                var im = new Inputmask("99.999.999/9999-99");
                im.mask(documento);
                return;
            }
        }

        return {
            add: _adicionar,
            edit: _editar,
            save: _salvar,
            remove: _remover,
            changeTipoPessoa: _changeTipoPessoa
        }
    }

    $(function () {
        window.ctrl = controlesController();
    });

})();