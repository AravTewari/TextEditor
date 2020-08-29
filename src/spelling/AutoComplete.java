/**
 * 
 */
package spelling;

import java.util.List;

/**
 * @author Arav Tewari
 *
 */
public interface AutoComplete
{
	public List<String> predictCompletions(String prefix, int numCompletions);
}
