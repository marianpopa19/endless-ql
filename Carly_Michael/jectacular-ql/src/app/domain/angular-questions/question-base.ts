import { FormGroup } from '@angular/forms';

export class QuestionBase<T> {
  value: T;
  key: string;
  label: string;
  required: boolean;
  order: number;
  controlType: string;
  hiddenCondition: (form: FormGroup) => boolean;
  readonly: boolean;

  constructor(options: {
    value?: T,
    key?: string,
    label?: string,
    required?: boolean,
    order?: number,
    controlType?: string,
    hiddenCondition?: (form: FormGroup) => boolean
    readonly?: boolean
  } = {}) {
    this.value = options.value;
    this.key = options.key || '';
    this.label = options.label || '';
    this.required = !!options.required;
    this.order = options.order === undefined ? 1 : options.order;
    this.controlType = options.controlType || '';
    this.hiddenCondition = options.hiddenCondition || (() => true);
    this.readonly = options.readonly || false;
  }
}
