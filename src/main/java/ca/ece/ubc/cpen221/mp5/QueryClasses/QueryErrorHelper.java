package ca.ece.ubc.cpen221.mp5.QueryClasses;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class QueryErrorHelper extends BaseErrorListener {
	

		@Override
		public void syntaxError(Recognizer<?, ?> recognizer, Object Offence, int i, int j,
				String s, RecognitionException ex) {

			// Throwing an error
			throw new RuntimeException(s);
		}

	}


