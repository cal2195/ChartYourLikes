package com.martin.cal.chartyourlikes.charts;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.martin.cal.chartyourlikes.charts.YearBarChartActivity;
import com.martin.cal.chartyourlikes.data.Movies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class YearTest {

    @Rule
    public ActivityTestRule<YearBarChartActivity> rule = new ActivityTestRule<>(YearBarChartActivity.class);

    @Test
    public void testYearBarChartData() throws JSONException {

        Movies.movies = new Movies();
        Movies.movies.movieData = new JSONArray("[{\"name\":\"Ratatouille\",\"genre\":\"Animation \",\"starring\":\"Patton Oswalt\\t (Remy), Ian Holm (Skinner), Lou Romano (Linguini), Brian Dennehy  (Django), Peter Sohn (Emile), Peter O'Toole (Anton Ego), Brad Garrett (Gusteau),   Janeane Garofalo (Colette), Will Arnett (Horst) \",\"release_date\":\"20070629\",\"studio\":\"Disney•Pixar\",\"directed_by\":\"Brad Bird and Jan Pinkava \",\"produced_by\":\"John Lasseter, Andrew Stanton, and Galyn Susman\",\"id\":\"17377140236\"},{\"name\":\"Pirates of the Caribbean\",\"genre\":\"Comedy-Adventure\",\"starring\":\"Johnny Depp, Penelope Cruz, Ian McShane, Kevin R. McNally,  Astrid Berges-Frisbey, Sam Claflin and Geoffrey Rush \",\"release_date\":\"20180500\",\"studio\":\"Walt Disney Studios\",\"directed_by\":\"Rob Marshall\",\"produced_by\":\"Jerry Bruckheimer \",\"id\":\"151276988262484\"},{\"name\":\"Brave\",\"genre\":\"Animation\",\"starring\":\"Kelly Macdonald, Billy Connolly, Emma Thompson, Kevin McKidd, Craig Ferguson, Robbie Coltrane, Julie Walters\",\"studio\":\"Disney•Pixar\",\"directed_by\":\"Mark Andrews & Brenda Chapman \",\"produced_by\":\"Katherine Sarafian \",\"id\":\"156129274416840\"},{\"name\":\"A-Team Movie\",\"genre\":\"Action | Adventure\",\"starring\":\"Liam Neeson, Bradley Cooper, Quinton “Rampage” Jackson, Sharlto Copley, Jessica Biel, Patrick Wilson, Gerald McRaney\",\"release_date\":\"20101214\",\"studio\":\"20th Century FOX\",\"directed_by\":\"Joe Carnahan\",\"produced_by\":\"Executive Producers: Ridley Scott, Marc Silvestri, Ross Fanger. Producers: Stephen J. Cannell, Spike Seldin, Tony Scott, Jules Daly, Alex Young and Iain Smith\",\"id\":\"172855809560\"},{\"name\":\"Avatar\",\"genre\":\"Blu-ray\\/DVD\",\"starring\":\"Zoe Saldana, Sam Worthington, Sigourney Weaver, Michelle Rodriguez, Giovanni Ribisi\",\"studio\":\"20th Century Fox \",\"directed_by\":\"James Cameron\",\"produced_by\":\"James Cameron, Jon Landau, Colin Wilson\",\"id\":\"82771544063\"},{\"name\":\"Up\",\"genre\":\"Animation\",\"starring\":\"Edward Asner, Christopher Plummer, Jordan Nagai, Bob Peterson, Delroy Lindo\",\"release_date\":\"20090529\",\"studio\":\"Disney•Pixar\",\"directed_by\":\"Pete Docter and Bob Peterson (co-director)\",\"produced_by\":\"Jonas Rivera\",\"id\":\"118194755634\"},{\"name\":\"Diary of a Wimpy Kid Movie\",\"genre\":\"Comedy | Family \",\"starring\":\"Jason Ian Drucker, Charlie Wright, Owen Asztalos, Tom Everett Scott, Alicia Silverstone\",\"release_date\":\"20170519\",\"studio\":\"20th Century Fox\",\"directed_by\":\"David Bowers\",\"produced_by\":\"Nina Jacobson, Brad Simpson\",\"id\":\"261188703422\"},{\"name\":\"Sully Movie\",\"release_date\":\"20160909\",\"studio\":\"Warner Bros.\",\"directed_by\":\"Clint Eastwood\",\"id\":\"1721296791431551\"},{\"name\":\"Forrest Gump\",\"genre\":\"Comedy, Drama, Romance\",\"awards\":\"Best Picture, Best Actor in a Leading Role (Tom Hanks), Best Director (Robert Zemeckis), Best Visual Effects, Best Film Editing, Best Writing\",\"starring\":\"Tom Hanks , Robin Penn , Gary Sinise , Mykelti Williamson , Sally Field , Peter Bannon , Hallie D'Amore , Hanna Hall , Michael Humphreys , Haley Joel Osment , Jeffrey Winner , Joe Washington , Sam Anderson\",\"release_date\":\"19940706\",\"studio\":\"Paramount Pictures\",\"directed_by\":\"Robert Zemeckis\",\"id\":\"11471355815\"},{\"name\":\"The Nightmare Before Christmas\",\"genre\":\"Animation, Holiday, Family\",\"starring\":\"Chris Sarandon, Danny Elfman, and Catherine O’Hara \",\"studio\":\"Walt Disney Studios\",\"directed_by\":\"Henry Selick\",\"produced_by\":\"Tim Burton, Danny Elfman, and Denise DiNovi\",\"id\":\"173587329354820\"},{\"name\":\"Toy Story\",\"genre\":\"Animation\",\"awards\":\"Winner of 2 Academy Awards®\\n- Best Animated Feature (Lee Unkrich)\\n- Best Original Song - “We Belong Together\\\" by Randy Newman\",\"starring\":\"Tom Hanks (Woody), Tim Allen (Buzz Lightyear), Don Rickles (Mr. Potato Head),   Wallace Shawn (Rex), John Ratzenberger (Hamm), Ned Beatty (Lotso), Buttercup (Jeff Garlin), Trixie (Kristen Schaal), Ken (Michael Keaton)\",\"release_date\":\"20100618\",\"studio\":\"Disney•Pixar\",\"directed_by\":\"Lee Unkrich\",\"produced_by\":\"Darla K. Anderson and John Lasseter\",\"id\":\"10498014129\"},{\"name\":\"Inception\",\"genre\":\"Action, Science Fiction \",\"awards\":\"2010 - AFI Film Award: AFI Movie of the Year\\n2011 - Os\"}]");

        //// TODO: 24/05/17
        YearBarChartActivity yearBarChartActivity = rule.getActivity();


        assertThat(yearBarChartActivity.processData(Movies.movies).toString(), is("[Entry, x: 2007.0 y: 1.0, Entry, x: 1994.0 y: 1.0, Entry, x: 2009.0 y: 1.0, Entry, x: 2017.0 y: 1.0, Entry, x: 2010.0 y: 2.0, Entry, x: 2016.0 y: 1.0, Entry, x: 2018.0 y: 1.0]"));
    }
}
