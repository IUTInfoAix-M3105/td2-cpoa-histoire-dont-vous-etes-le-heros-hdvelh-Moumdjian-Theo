
package pracHDVELH;

public class EventExactSolution extends Event {

	private static final int SUCCESS_EVENT_INDEX = 0;
	private static final int ERROR_EVENT_INDEX = 1;
	
	private String exactAnswer;
	
	private int precision;

	
	@Override
	public int interpretAnswer() {
		if (getanswer() == null || isFinal() || !isInRange(0) || !isInRange(1) || exactAnswer == null)
			return -1;
		
		if(precision == 0)
			return SUCCESS_EVENT_INDEX;

		if (getanswer().startsWith(exactAnswer.substring(0, precision)))
			return SUCCESS_EVENT_INDEX;

		return ERROR_EVENT_INDEX;
	}

	public void setSuccessErrorEvents(Event successEvent, Event errorEvent) {
		setDaughter(successEvent, SUCCESS_EVENT_INDEX);
		setDaughter(errorEvent, ERROR_EVENT_INDEX);
	}
	

	public String getExactAnswer() {
		return this.exactAnswer;
	}

	public int getPrecision() {
		return this.precision;
	}
	

	public void setExactAnswer(String exactAnswer) {
		if(exactAnswer == null)
			setExactAnswer(exactAnswer, 0);
		else
			setExactAnswer(exactAnswer, exactAnswer.length());
	}
	
	

	public void setExactAnswer(String exactAnswer, int precision) {
		this.exactAnswer = exactAnswer;
		setPrecision(precision);
	}

	public void setPrecision(int precision) {
		if (precision < 0 || exactAnswer == null)
			this.precision = 0;
		else if (precision > exactAnswer.length())
			this.precision = exactAnswer.length();
		else
			this.precision = precision;
	}


	public EventExactSolution() {
		this("");
	}

	public EventExactSolution(String exactAnswer) {
		this(new GUIManager(), "", exactAnswer);
	}

	public EventExactSolution(GUIManager gui, String data, String exactAnswer) {
		this(gui, data, exactAnswer, (exactAnswer == null ? 0 : exactAnswer.length()));
	}

	public EventExactSolution(GUIManager gui, String data, String exactAnswer, int precision) {
		super(gui, data);
		setExactAnswer(exactAnswer, precision);
	}
}

