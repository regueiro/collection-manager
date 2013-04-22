/* EnumUserType utility class
 * 
 * Created by raisercostin
 * 
 * http://stackoverflow.com/a/2689615
 * http://stackoverflow.com/users/99248/raisercostin
 */

package es.regueiro.collectionManager.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

public class EnumUserType implements UserType, ParameterizedType {

    private static final int[] SQL_TYPES = { Types.VARCHAR };
    @SuppressWarnings("rawtypes")
	private Class clazz = null;

    public EnumUserType() {
    }

    @Override
    public void setParameterValues(Properties parameters) {
        String className = (String) parameters.get("type");
        try {
            this.clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Couldn't get the class for name [" + className + "].", e);
        }
    }

    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @SuppressWarnings("rawtypes")
	public Class returnedClass() {
        return clazz;
    }
    @SuppressWarnings("unchecked")
	public Object nullSafeGet(ResultSet resultSet, String[] names,
			SessionImplementor sessionImplementator, Object owner) throws HibernateException,
			SQLException {
        String name = resultSet.getString(names[0]);
        Object result = null;
        if (!resultSet.wasNull()) {
            result = Enum.valueOf(clazz, name);
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index,
			SessionImplementor sessionImplementator) throws HibernateException,
            SQLException {
        if (null == value) {
            preparedStatement.setNull(index, Types.VARCHAR);
        } else {
            preparedStatement.setString(index, ((Enum) value).name());
        }
    }

    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    public boolean isMutable() {
        return false;
    }

    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        }
        if ((null == x) || (null == y)) {
            return false;
        }
        return x.equals(y);
    }


}