import {ParseFactory, ParseResult} from './parse-factory';
import {parse} from '../../parser/ql-parser';
import {parse as parseQls} from '../../parser/qls-parser';
import {Form} from '../domain/ast';
import {Stylesheet} from '../domain/ast/qls';

export class ParseQlWithQlsFactory implements ParseFactory {
  constructor(private ql: string, private qls: string) { }

  parse(): ParseResult {
    const astQl: Form = parse(this.ql, {});
    const astQls: Stylesheet = parseQls(this.qls, {});
    // check form
    astQl.checkForm();

    return {formName: astQl.name, qlForm: astQl, qlsStylesheet: astQls};
  }
}