package com.mantisadnetwork.android.admob;

/**
 * The context is a shared configuration that is used by the MANTIS mediation layer.
 * 
 */
public class Context {
	private String title;
	private String screen;
	private String propertyId;

	private static Context CONTEXT;

	static {
		CONTEXT = new Context();
	}

	private Context() {
		// Force use of singleton
	}

	public static Context get() {
		return CONTEXT;
	}

	/**
	 * @return The title specific to the current screen.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Screens are often reused but the context of which it is displayed can change. For example, a user may select an
	 * item to view more information on the "Item View" screen, but the title would be the name of that item like
	 * "Item 1". This should be set every time the user changes views/screens.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return The screen the user is currently on.
	 */
	public String getScreen() {
		return screen;
	}

	/**
	 * The screen is a generic name given to a view/screen that the user is on. For example, a user viewing a list of
	 * businesses may have a screen of "Business List", but when selecting a business to get more information, it would
	 * have a screen of "Business View". This should be set every time the user changes views/screens.
	 */
	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getPropertyId() {
		return propertyId;
	}

	/**
	 * The property ID that can be found in the MANTIS administration panel.
	 */
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
}
