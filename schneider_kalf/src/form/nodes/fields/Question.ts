import FieldType from "../../field/FieldType";
import Field from "./Field";
import NodeVisitor from "../visitors/NodeVisitor";

export default class Question implements Field {
  readonly label: string;
  readonly identifier: string;
  readonly type: FieldType;

  constructor(identifier: string, label: string, type: FieldType) {
    this.label = label;
    this.identifier = identifier;
    this.type = type;
  }

  accept(visitor: NodeVisitor): any {
    return visitor.visitQuestion(this);
  }
}