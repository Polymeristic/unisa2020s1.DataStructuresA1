package ds.students;

import ds.students.Token.Type;

import java.util.EmptyStackException;
import java.util.IllegalFormatException;

/**
 * @author simont
 *
 */
public class Calculator {

	/**
	 * Converts an 'infix' expression into a 'postfix' expression
	 * @param infix infix expression queue
	 * @return postfix expression queue
	 */
	public DSQueue infixToPostfix(DSQueue infix) {
		DSStack operators = new DSStack();
		DSQueue postfix = new DSQueue();

		while (!infix.isEmpty()) {
			Token t = infix.poll();

			if (t.type == Type.OPERAND) {
				postfix.offer(t);
			} else

			if (t.type == Type.PAREN) {
				if (t.getOperator().equals("(")) {
					operators.push(t);
				} else {
					Token r = operators.pop();

					// Relocate everything back up until the next ( occurs
					while (!r.getOperator().equals("(")) {
						postfix.offer(r);
						r = operators.pop();
					}
				}
			} else

			if (t.type == Type.OPERATOR) {
				// Relocate operators with greater/equal precedence
				while (!operators.isEmpty() &&
						operators.peek().getPrecedence() >= t.getPrecedence()) {
					postfix.offer(operators.pop());
				}

				operators.push(t);
			}
		}

		while (!operators.isEmpty()) {
			postfix.offer(operators.pop());
		}

		return postfix;
	}

	/**
	 * Evaluates a postfix expression queue
	 * @param exp postfix expression queue
	 * @return the evaluated expression
	 */
	public double evaluatePostfix(DSQueue exp)  {
		DSStack evalStack = new DSStack();

		while (!exp.isEmpty()) {
			Token t = exp.poll();

			// If op just add
			if (t.type == Type.OPERAND) {
				evalStack.push(t);
			} else

			// If operator then evaluate the previous
			if (t.type == Type.OPERATOR) {
				// Take two items from the stack (first pop is second operand)
				Token a = evalStack.pop();
				Token b = evalStack.pop();

				// Eval and put back on stack
				evalStack.push(t.evaluate(b, a));
			}
		}

		return evalStack.pop().getOperand();
	}
}
